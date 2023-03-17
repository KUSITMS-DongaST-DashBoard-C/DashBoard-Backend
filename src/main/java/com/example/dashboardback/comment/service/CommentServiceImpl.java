package com.example.dashboardback.comment.service;

import com.example.dashboardback.comment.dto.CommentDto;
import com.example.dashboardback.comment.dto.CommentDto.CreateResponse;
import com.example.dashboardback.comment.dto.CommentDto.GetResponse;
import com.example.dashboardback.comment.dto.CommentMapper;
import com.example.dashboardback.comment.entity.Comment;
import com.example.dashboardback.comment.exception.NotFoundCommentException;
import com.example.dashboardback.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.dashboardback.comment.dto.CommentDto.GetResponse.convertCommentToDto;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;


    @Override
    public CreateResponse createComment(CommentDto.CreateRequest createRequest) {
        Comment comment = this.commentMapper.toEntity(createRequest);
        return this.commentMapper.toCreateResponse(this.commentRepository.save(comment));
    }

    @Override
    public Comment deleteComment(CommentDto.DeleteRequest deleteRequest) {
        Comment comment = this.validateCommentId(deleteRequest.getCommentId());
        comment.setDeleted(true);
        return comment;
    }

    @Override
    public List<GetResponse> getAllCommentsByBoardId(Long memoId) {
        List<Comment> allCommentsByBoardId = this.commentRepository.findAllCommentsByMemoId(memoId);
        List<GetResponse> result = new ArrayList<>();
        Map<Long, GetResponse> map = new HashMap<>();
        allCommentsByBoardId.stream().forEach(c -> {
            GetResponse getResponse = convertCommentToDto(c);
            map.put(getResponse.getCommentId(), getResponse);
            result.add(getResponse);
        });
        return result;
    }

    @Override
    public Comment validateCommentId(Long commentId) {
        return this.commentRepository.findNotDeletedByCommentId(commentId).orElseThrow(NotFoundCommentException::new);
    }
}
