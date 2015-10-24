# HP Developer Day 2015 - Łódź

## Pietryna

### Modules

* [Java backend](backend-java/README.md)
* [.NET backend](backend-net/README.md)
* [HTML frontend](frontend-html/README.md)
* [Database](database/README.md)

### API

| Method | Path | Path variables | Path params | Request body | Response status | Response | Description |
| ------ | ---- | -------------- | ----------- | ------------ | --------------- | -------- | ----------- |
| GET | /api/places | | | | 200 | JSON array of places | Get list of places |
| GET | /api/friends | | | | 200 | JSON array of friends | Get list of friends |
| POST | /api/friends | | | JSON friend object (required: id) | 201 | JSON friend object | Create a friend |
| DELETE | /api/friends/{id} | Friend id | | | 204 | | Delete a friend by id |
| GET | /api/users | | | | 200 | JSON array of users | Get list of users |
| GET | /api/users/me | | | | 200 | JSON user object | Get an authenticated user |
