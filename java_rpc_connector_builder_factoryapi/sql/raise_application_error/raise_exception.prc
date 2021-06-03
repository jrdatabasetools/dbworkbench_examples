create or replace procedure raise_exception
/**
 * Demonstration of handling user and runtime PL/SQL exceptions.<br>
 * Just have a look at the stacktrace. The call hierarchy of the java methods and PL/SQL stored procedures 
 * are displayed with line numbers.
 */
is
begin
  raise_application_error(-20000, 'just throw an exception displaying this text information');
end raise_exception;