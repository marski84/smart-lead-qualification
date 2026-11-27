ALTER TABLE leads
    ADD COLUMN message_id BIGINT;

ALTER TABLE leads
    ALTER COLUMN title SET NOT NULL;
ALTER TABLE leads
    ALTER COLUMN type SET NOT NULL;
ALTER TABLE leads
    ALTER COLUMN urgency SET NOT NULL;
ALTER TABLE leads
    ALTER COLUMN summary SET NOT NULL;

ALTER TABLE leads
    ADD CONSTRAINT uq_leads_message_id UNIQUE (message_id);
ALTER TABLE leads
    ADD CONSTRAINT chk_leads_type
        CHECK (type IN ('DEMO_REQUEST', 'PRICING_INQUIRY', 'PARTNERSHIP', 'SUPPORT', 'OTHER'));
ALTER TABLE leads
    ADD CONSTRAINT chk_leads_urgency
        CHECK (urgency IN ('LOW', 'MEDIUM', 'HIGH'));
