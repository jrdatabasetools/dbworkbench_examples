create or replace package body xml_type_demo
as

procedure get_xml_type
(
  xml out xmltype 
)
is
begin
  xml := dbms_xmlgen.getxmltype('select 1 as v1, 1.1 as v2, to_date(''1999/01/01'', ''yyyy/mm/dd'') as v3, ''string_1'' as v4 from dual ' ||
                                 'union select 2, 2.2, to_date(''2000/2/2'', ''yyyy/mm/dd''), ''string_2'' from dual ' ||
                                 'union select 3, 3.3, to_date(''2001/3/3'', ''yyyy/mm/dd''), ''string_3'' from dual');
end get_xml_type;

procedure extract_from_xml_type
(
  xml       in  xmltype,
  name      out varchar2,
  surename  out varchar2
)
is
begin
  select  trim(extractvalue(xml, '/ROW/NAME')),
          trim(extractvalue(xml, '/ROW/SURENAME'))
    into  name,
          surename
    from  dual;
end extract_from_xml_type;

end xml_type_demo;