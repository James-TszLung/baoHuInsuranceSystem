-- 定时任务 ， 预付款订单2小时后改为技术管家已接单
drop procedure if exists receipt1  ;
create PROCEDURE receipt1()
BEGIN
DECLARE time DATETIME;
DECLARE order_ids VARCHAR(32);
DECLARE order_ids_temp VARCHAR(32);
DECLARE counts INT DEFAULT 0;
DECLARE i INT DEFAULT 0;
DECLARE processpay_money_temp BIGINT;
DECLARE order_ids_list CURSOR for select o.order_id order_ids from xcx_order_temp o;
set i=0;
set processpay_money_temp = 0;
set counts = (select count(order_id) from xcx_order_temp );
select counts;
set time = (select date_sub(now(), interval 2 hour) );
select time;
OPEN order_ids_list;
REPEAT
	FETCH order_ids_list into order_ids ;  -- 将游标当前读取行的数据顺序赋予自定义变量12  
	select order_ids;
	SELECT o.id,o.processpay_money INTO order_ids_temp,  processpay_money_temp from xcx_order o where o.id = order_ids and o.prepare_paysuc_time<time and o.tgysteward_id is not null and o.tgysteward_id <> '';
	select order_ids_temp;
	IF order_ids_temp is not NULL THEN
		select ('空1');
		select processpay_money_temp;
		IF ((processpay_money_temp is null) OR (processpay_money_temp=0)) THEN
			select ('processpay_money_temp is null');
			update xcx_order o set o.takeorder_time=now(),o.status=9 where o.id =order_ids;
			delete from xcx_order_temp  where  order_id =order_ids;
		ELSE
			select ('processpay_money_temp not null');
			update xcx_order o set o.takeorder_time=now(),o.status=8 where o.id =order_ids;
			delete from xcx_order_temp  where  order_id =order_ids;
		END IF;
	ELSE
		select ('空2');
	END IF;
	set i = i+1;
	select i;
UNTIL i>=counts END REPEAT; #当_done=1时退出被循  
CLOSE order_ids_list;
end;

-- 创建定时任务，每分钟定时执行
CREATE DEFINER=`root`@`localhost` EVENT `receipt1_event`
ON SCHEDULE EVERY 60 SECOND STARTS now()
ON COMPLETION NOT PRESERVE ENABLE
DO CALL `receipt1`



drop EVENT receipt1_event

call receipt1()







SET GLOBAL event_scheduler=on;
