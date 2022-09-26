create table flat (
                             id bigserial primary key,
                             name text not null,
                             coordinates_x float,
                             coordinates_y int not null,
                             creation_date timestamp without time zone,
                             area double precision,
                             number_of_rooms bigint,
                             height integer,
                             new_field boolean not null,
                             transport character varying(255) not null,
                             house_name text not null,
                             house_year int,
                             house_number_of_floors bigint,
                             house_number_of_lifts bigint,
                             price double precision
);

