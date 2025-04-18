SELECT NAME, DATETIME
FROM animal_ins
WHERE animal_id NOT IN (SELECT animal_id FROM animal_outs)
ORDER BY datetime
LIMIT 3