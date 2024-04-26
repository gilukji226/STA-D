package com.klpc.stadspring.domain.contents.bookmark.service.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteBookmarkRequsetCommand {
    private Long userId;
    private Long detailId;
}
