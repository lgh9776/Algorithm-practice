WITH fish_max_len AS (
SELECT fish_info.fish_type, max(length) as len
FROM fish_info
GROUP BY fish_info.fish_type),

fish_max_with_id AS (
SELECT id, fml.fish_type, fml.len
FROM fish_info fi JOIN fish_max_len fml
ON fi.fish_type = fml.fish_type
AND fi.length = fml.len
ORDER BY id )

SELECT id AS ID, fish_name AS FISH_NAME, fmwi.len AS LENGTH
FROM fish_max_with_id fmwi JOIN fish_name_info fni
ON fmwi.fish_type = fni.fish_type
