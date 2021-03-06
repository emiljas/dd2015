# HP Developer Day 2015 - Łódź

## HTML Frontend

### Prerequisites

- GIT in your system PATH
- Node.js [http://nodejs.org](http://nodejs.org)
- Bower installed [http://bower.io](http://bower.io)

```bash
npm install -g bower
```

- Gulp installed [http://gulpjs.com](http://gulpjs.com)

```bash
npm install -g gulp
```

### Developer environment setup

#### Configure:

Inside frontend-html directory run:

```bash
npm install
bower install
```

Edit server.middleware in [server.js](gulp/server.js) to set the correct URL mapping for your backend server:

```
server.middleware = proxyMiddleware('/api', {
    target: 'http://localhost:9000',
    proxyHost: 'localhost'
  });
```

#### Build distribution package:

```bash
gulp clean
gulp build
```

#### Run on embedded web server:

```bash
gulp serve
```

### Adding new dependencies

#### Npm

```bash
npm install <package_name> --save-dev
```

##### Option 2

Update [package.json](package.json) 

Run:

```bash
npm install
```

#### Bower

##### Option 1

```bash
bower install <package_name> --save
```

##### Option 2

Update [bower.json](bower.json)

Run:

```bash
bower install
```

