WITH max_len_info AS (
    SELECT fish_type, MAX(length) AS max_length
    FROM fish_info
    WHERE length > 10 -- 10cm 이하 제외
    GROUP BY fish_type
),
ranked_fish AS (
    SELECT fi.id, fi.fish_type, fi.length,
           ROW_NUMBER() OVER (PARTITION BY fi.fish_type ORDER BY fi.id) AS rn
    FROM fish_info fi
    JOIN max_len_info mli
    ON fi.fish_type = mli.fish_type AND fi.length = mli.max_length
)
SELECT r.id AS ID, fni.fish_name AS FISH_NAME, r.length AS LENGTH
FROM ranked_fish r
JOIN fish_name_info fni ON r.fish_type = fni.fish_type
WHERE r.rn = 1 -- 가장 큰 물고기 중 ID가 가장 작은 1마리만 선택
ORDER BY r.id;
