create table appointment ( 
    client_id varchar(20) not null,
    costline_id varchar(20) not null,
    flight_num varchar(20) not null,
    count int,
    grade varchar(20), 
    app_date TIMESTAMP DEFAULT current_timestamp on update current_timestamp,
    primary key(client_id, costline_id, flight_num, count),
    foreign key(client_id) references client(id),
    foreign key(costline_id) references cost(line_id),
    foreign key(flight_num) references flying(flight_num)
)ENGINE = INNODB;

INSERT INTO appointment(client_id, costline_id, flight_num, count, air_group, grade) VALUES ('kut001', 'EU0010', 'EUR0010', 1, '일반석'); 
INSERT INTO appointment(client_id, costline_id, flight_num, count, air_group, grade) VALUES ('kut001', 'KR0010', 'KOR0010', 1, '일반석');