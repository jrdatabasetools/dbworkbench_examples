create or replace function binary_types
(
  i_bin_float   in  binary_float, 
  i_bin_double  in  binary_double, 
  i_bin_integer in  binary_integer,
  o_bin_float   out binary_float,
  o_bin_double  out binary_double
)
return binary_integer
/**
 * Demo of binary type handling.
 * @param i_bin_float 32-bit floating point value.
 * @param i_bin_double 64-bit floating point value.
 * @param i_bin_integer 32-bit integer value.
 * @param o_bin_float 32-bit floating point value
 * @param o_bin_double  64-bit floating point value.
 * @return 32-bit integer
 */
is
begin
  o_bin_float := i_bin_float * 3.14159265359;
  o_bin_double := i_bin_double * 3.14159265359;
  return i_bin_integer * 3;
end binary_types;