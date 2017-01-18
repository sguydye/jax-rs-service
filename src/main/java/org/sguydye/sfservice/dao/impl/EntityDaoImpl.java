package org.sguydye.sfservice.dao.impl;

import org.apache.cxf.common.util.CollectionUtils;
import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class EntityDaoImpl extends GenericDaoImpl<LogicalEntity, Integer> implements EntityDao {
    @Override
    public List<LogicalEntity> findByName(String name) {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<LogicalEntity> criteriaQuery = criteriaBuilder.createQuery(LogicalEntity.class);
        Root<LogicalEntity> root = criteriaQuery.from(LogicalEntity.class);
        criteriaQuery.select(root).where(
                criteriaBuilder.equal(root.get("name"), name)
        );
        return currentSession().createQuery(criteriaQuery).getResultList();
    }

    @Override
    public void update(LogicalEntity entity) {

        if(!CollectionUtils.isEmpty(entity.getConstraints())) {
            entity.getConstraints().forEach(logicalConstraint -> {
                logicalConstraint.setEntity(entity);
            });
        }
        if(!CollectionUtils.isEmpty(entity.getFields())){
            entity.getFields().forEach(logicalField -> {
                logicalField.setEntity(entity);
            });
        }
        super.update(entity);
    }

    @Override
    public void save(LogicalEntity entity) {
        if(!CollectionUtils.isEmpty(entity.getConstraints())) {
            entity.getConstraints().forEach(logicalConstraint -> {
                logicalConstraint.setEntity(entity);
            });
        }
        if(!CollectionUtils.isEmpty(entity.getFields())){
            entity.getFields().forEach(logicalField -> {
                logicalField.setEntity(entity);
            });
        }
        super.save(entity);
    }
}
