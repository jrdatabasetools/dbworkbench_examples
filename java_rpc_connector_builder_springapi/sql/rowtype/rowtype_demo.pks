create or replace package rowtype_demo
as
/**
 * Rowtype Demo.
 */

/**
 * Input and output parameter of type rowtype.
 *
 * @param io_rec Input parameter record value is modified and returned.
 */
procedure modify_record(io_rowtype_example in out rowtype_example%rowtype);

end rowtype_demo;