create table Flightrecord ( 
    client_id varchar(20) not null,
    start_place varchar(20),
    end_place varchar(20),
    waste_time varchar(20),
    total_cost varchar(20),
    seat_sort varchar(20),
    total_plane_seat varchar(20),
    flight_num varchar(20),
    airplane_id varchar(20),
    total_user varchar(20),
    foreign key(client_id) references client(id)
)ENGINE = INNODB;
