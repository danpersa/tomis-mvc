package org.tomis.mvc.model.service.jpa;

import org.tomis.mvc.model.dto.Dto;

/**
 * Project: tomis-mvc
 * @since 12.08.2009
 * @author Dan Persa
 */
public interface EditableService<CommandObject extends Dto> {

    CommandObject getForEdit(Long id);

    Long save(CommandObject commandObject);
}
