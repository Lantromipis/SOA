package se.ifmo.ru.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {
    private List<T> objects;
    private Integer page;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalCount;
}
