package com.zrana.springit.Repository;

import com.zrana.springit.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentInterface extends JpaRepository<Comment, Long> {
}
