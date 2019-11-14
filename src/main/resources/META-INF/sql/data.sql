create trigger Public after insert on users for each row
    begin
    insert into user_chatroom(user_id,chatroom_id) values (new.id,1);
end