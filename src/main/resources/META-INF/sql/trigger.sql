create trigger USERROLE after insert on users for each row
    begin
    insert into authorities(username,authority) values (new.username,'ROLE_USER');
end

