WITH safe_period AS (
    SELECT outs.animal_id AS id, DATEDIFF(outs.datetime, ins.datetime) AS period
    FROM animal_ins AS ins LEFT JOIN animal_outs AS outs
    ON ins.animal_id = outs.animal_id
    ORDER BY period DESC
    LIMIT 2
)

SELECT ANIMAL_ID, NAME
FROM animal_outs
WHERE animal_id IN (SELECT id FROM safe_period)

