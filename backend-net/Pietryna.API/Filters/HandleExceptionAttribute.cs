namespace Pietryna.API.Filters
{
    using System.Web.Http.Filters;
    using log4net;

    public class HandleExceptionAttribute : ExceptionFilterAttribute
    {
        public override void OnException(HttpActionExecutedContext actionExecutedContext)
        {
            ILog log = LogManager.GetLogger(actionExecutedContext.ActionContext.ControllerContext.Controller.GetType().Name);
            log.ErrorFormat(actionExecutedContext.Exception.StackTrace);

            base.OnException(actionExecutedContext);
        }
    }
}