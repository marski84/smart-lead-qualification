package org.grow.smartleadqualification.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MessageRepository extends JpaRepository<Message, Long> {
}
