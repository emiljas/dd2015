namespace Pietryna.API.Filters
{
    using System;
    using System.Collections.Generic;
    using System.Linq;
    using System.Net.Http.Headers;
    using System.Security.Claims;
    using System.Security.Principal;
    using System.Text;
    using System.Threading;
    using System.Threading.Tasks;
    using System.Web.Http.Filters;
    using System.Web.Http.Results;
    using DataModels;
    using log4net;

    public class BasicAuthenticationAttribute : Attribute, IAuthenticationFilter
    {
        protected readonly ILog Log = LogManager.GetLogger(typeof (BasicAuthenticationAttribute).Name);

        public async Task AuthenticateAsync(HttpAuthenticationContext context, CancellationToken cancellationToken)
        {
            this.Log.Debug("Trying authenticate ...");
            AuthenticationHeaderValue authorization = context.Request.Headers.Authorization;

            if (authorization == null)
            {
                return;
            }

            if (authorization.Scheme != "Basic")
            {
                return;
            }

            if (string.IsNullOrEmpty(authorization.Parameter))
            {
                context.ErrorResult = new UnauthorizedResult(new AuthenticationHeaderValue[0], context.Request);
                return;
            }

            Tuple<string, string> userNameAndPasword = this.ExtractUserNameAndPassword(authorization.Parameter);

            if (userNameAndPasword == null)
            {
                context.ErrorResult = new UnauthorizedResult(new AuthenticationHeaderValue[0], context.Request);
                return;
            }

            string userName = userNameAndPasword.Item1;

            this.Log.DebugFormat("Username: {0}", userName);

            IPrincipal principal = await this.AuthenticateAsync(userName);

            if (principal == null)
            {
                this.Log.Debug("Invalid username");
                context.ErrorResult = new UnauthorizedResult(new AuthenticationHeaderValue[0], context.Request);
            }
            else
            {
                context.Principal = principal;
                this.Log.Debug("User authenticated! ");
            }
        }

        public Task ChallengeAsync(HttpAuthenticationChallengeContext context, CancellationToken cancellationToken)
        {
            return Task.FromResult(0);
        }

        public bool AllowMultiple
        {
            get { return false; }
        }

        private async Task<IPrincipal> AuthenticateAsync(string userName)
        {
            using (var dbContext = new AppDbContext())
            {
                if (dbContext.Users.All(u => u.Email != userName))
                {
                    return null;
                }
            }

            var claims = new List<Claim>
            {
                new Claim(ClaimTypes.Name, userName)
            };

            IIdentity identity = new ClaimsIdentity(claims, AuthenticationTypes.Basic);

            IPrincipal principal = new ClaimsPrincipal(identity);
            return principal;
        }

        private Tuple<string, string> ExtractUserNameAndPassword(string authorizationParameter)
        {
            byte[] credentialBytes;

            try
            {
                credentialBytes = Convert.FromBase64String(authorizationParameter);
            }
            catch (FormatException)
            {
                return null;
            }

            Encoding encoding = Encoding.ASCII;
            encoding = (Encoding) encoding.Clone();
            encoding.DecoderFallback = DecoderFallback.ExceptionFallback;
            string decodedCredentials;

            try
            {
                decodedCredentials = encoding.GetString(credentialBytes);
            }
            catch (DecoderFallbackException)
            {
                return null;
            }

            if (String.IsNullOrEmpty(decodedCredentials))
            {
                return null;
            }

            int colonIndex = decodedCredentials.IndexOf(':');

            if (colonIndex == -1)
            {
                return null;
            }

            string userName = decodedCredentials.Substring(0, colonIndex);
            string password = decodedCredentials.Substring(colonIndex + 1);
            return new Tuple<string, string>(userName, password);
        }
    }
}