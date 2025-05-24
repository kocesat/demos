select * from task_worker order by worker_id;

INSERT INTO task_worker_registry (worker_id, worker_name, status)
SELECT generate_series(11, 99), '', 0
    ON CONFLICT DO NOTHING;

WITH next_available AS (
    SELECT worker_id
    FROM task_worker_registry
    WHERE status = 0 or update_time < current_timestamp - INTERVAL '10 minutes'
ORDER BY worker_id
    LIMIT 1
    FOR UPDATE SKIP LOCKED
            )
UPDATE task_worker_registry
SET
    worker_name = 'filetransfer-x008',
    update_time = current_timestamp,
    status = 1          -- claimed
WHERE worker_id IN (SELECT worker_id FROM next_available)
    RETURNING worker_id;
