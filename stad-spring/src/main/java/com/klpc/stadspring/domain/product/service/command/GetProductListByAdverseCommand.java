package com.klpc.stadspring.domain.product.service.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProductListByAdverseCommand {
    Long adverseId;
}