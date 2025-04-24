SELECT ugb.WRITER_ID AS USER_ID, ugu.NICKNAME, SUM(ugb.PRICE) AS TOTAL_SALES
FROM used_goods_board AS ugb RIGHT JOIN used_goods_user AS ugu
ON ugb.writer_id = ugu.user_id
WHERE ugb.status LIKE 'DONE'
GROUP BY ugb.writer_id
HAVING TOTAL_SALES >= 700000
ORDER BY TOTAL_SALES