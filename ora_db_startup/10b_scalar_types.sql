alter session set container = XEPDB1;
alter session set current_schema = dbw_examples;

create or replace package scalar_types
/**
 * Demonstration of using simple scalar types.
 */
as

/**
 * Function adds two parameter numbers and returning the sum.
 *
 * @param i_n1 First parameter.
 * @param i_n2 Second parameter.
 * @return Sum of both parameter.
 */
function add_num(i_n1 in number, i_n2 in number) return number;

/**
 * Function concatenates two parameter varchar2 and returning the concatenated varchar2.
 *
 * @param i_s1 First parameter.
 * @param i_s2 Second parameter.
 * @return Concatenated parameters.
 */
function concat_char(i_s1 in varchar2, i_s2 in varchar2) return varchar2;
 
end scalar_types;
/