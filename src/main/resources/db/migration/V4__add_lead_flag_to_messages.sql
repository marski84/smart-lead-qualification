ALTER TABLE messages
    ADD COLUMN is_lead BOOLEAN;

CREATE INDEX idx_messages_status_received_at ON messages (status, received_at);
