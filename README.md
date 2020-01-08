# rso-brewers
Microservice for brewers

```bash
docker run -d --name pg-brewers -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=brewers -p 5432:5432 postgres:10.5
```