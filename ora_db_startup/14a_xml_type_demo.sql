alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package xml_type_demo
/**
 * Demo of database type XMLTYPE represented as Java org.w3c.Document. 
 */
as

/**
 * Generates and returns a simple XML-Document.
 *
 * @param xml XML-Document.
 */
procedure get_xml_type
(
  xml out xmltype 
);

/**
 * Parses an XML-Document and returns two extracted fields.
 *
 * @param xml XML-Document to parse.
 * @param name Extracted field 'name' from XML-Document.
 * @param surename Extracted field 'surname' from XML-Document.
 */
procedure extract_from_xml_type
(
  xml       in  xmltype,
  name      out varchar2,
  surename  out varchar2
);

end xml_type_demo;
/