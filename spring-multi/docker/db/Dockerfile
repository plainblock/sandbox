FROM public.ecr.aws/docker/library/postgres:9.6.20

COPY *.sql /docker-entrypoint-initdb.d/

ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_USER=postgres
ENV POSTGRES_DB=multi
