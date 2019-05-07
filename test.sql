DROP PROCEDURE IF EXISTS `update_037`;;
CREATE PROCEDURE `update_037`(IN build_date date)
BEGIN
    CALL show_msg('>>CALL update_037();');

    -- RM 21107
    call drop_index(database(),'ports','t_index_portid');
    call drop_field(database(),'ports','virtual_id');

END;;