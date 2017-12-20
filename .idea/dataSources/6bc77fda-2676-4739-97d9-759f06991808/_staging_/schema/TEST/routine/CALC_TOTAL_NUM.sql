CREATE PROCEDURE calc_total_num
  AS
    names VARCHAR2(255);
  BEGIN
    SELECT a.NAME INTO names FROM ATEST a;
    dbms_output.put_line(names);
  END;
/
