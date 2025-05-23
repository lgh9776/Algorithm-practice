SELECT COUNT(id) AS FISH_COUNT, MAX(length) AS MAX_LENGTH, FISH_TYPE
FROM fish_info
GROUP BY fish_type
HAVING AVG(COALESCE(length, 10)) >= 33
ORDER BY fish_type
