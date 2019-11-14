create trigger USERROLE after insert on users for each row
    begin
    insert into authorities(username,authority) values (new.username,'ROLE_USER');
end

create trigger Public after insert on users for each row
    begin
    insert into user_chatroom(user_id,chatroom_id) values (new.id,1);
end