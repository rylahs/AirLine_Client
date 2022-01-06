create table refund ( 
    client_id varchar(20),
    costline_id varchar(20),
    flight_num varchar(20),
    count int,
    account varchar(20), 
    ref_date TIMESTAMP DEFAULT current_timestamp on update current_timestamp,
    primary key(client_id, costline_id, flight_num, count),
    foreign key(client_id) references client(id),
    foreign key(costline_id) references cost(line_id),
    foreign key(flight_num) references flying(flight_num)
)ENGINE = INNODB;


INSERT INTO refund(client_id, costline_id, flight_num, count, account) VALUES ('kut001', 'EU0010','EUR0010', 1, '1101111111'); 
INSERT INTO refund(client_id, costline_id, flight_num, count, account) VALUES ('kut001', 'KR0010','KOR0010', 2,'2222222222');
