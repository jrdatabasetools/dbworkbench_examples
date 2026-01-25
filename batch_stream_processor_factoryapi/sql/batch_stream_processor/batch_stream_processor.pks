create or replace package batch_stream_processor
/**
 * PL/SQL Batch/Stream processor demonstration.
 */
as

-- PL/SQL record row declaration for batch/stream processing
type rec_batch_row is record (
  no            number(9),
  description   varchar2(100),
  ts            timestamp
);

-- cursor definition
type c_batch_row is ref cursor return rec_batch_row;

/**
 * Functions returns one cursor for batch processing.
 *
 * @param i_number_of_rows Number of rows to generate.
 * @return Row of PL/SQL record
 */
function job1 (i_number_of_rows in number) return c_batch_row;

end batch_stream_processor;