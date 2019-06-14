#docker run -d -e "POSTGRES_PASSWORD=password" -e "POSTGRES_USER=postgres" -p 5432:5432 postgres | 
#xargs docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}'

#docker run -d -p 8888:80 -e "PGADMIN_DEFAULT_EMAIL=user@domain.com" \
#-e "PGADMIN_DEFAULT_PASSWORD=password" 'dpage/pgadmin4'


docker start adoring_kalam 
docker start zealous_carson
