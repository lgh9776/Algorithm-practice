SELECT concat('/home/grep/src/', board_id, '/', file_id, file_name, file_ext) AS FILE_PATH
FROM used_goods_file
WHERE board_id = (SELECT board_id 
                  FROM used_goods_board 
                  ORDER BY views DESC 
                  LIMIT 1)
ORDER BY file_id DESC