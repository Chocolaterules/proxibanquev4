package fr.formation.proxi4.metier.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/** Classe regroupant les 5 methodes du CRUD + readAll utilisées par les autres services.
 * @author Adminl
 *
 * @param <ENTITY>
 */
public abstract class RestService<ENTITY> {

	protected abstract JpaRepository<ENTITY, Integer> getDao();
	
	public ENTITY create(final ENTITY entity) {
		return this.getDao().save(entity);
	}
	
	public ENTITY read(final Integer id) {
		return this.getDao().getOne(id);
	}
	
	public List<ENTITY> readAll() {
		return this.getDao().findAll();
	}
	
	public ENTITY update(final ENTITY entity) {
		return this.getDao().save(entity);
	}
	
	public void delete(final Integer id) {
		this.getDao().deleteById(id);
	}
}
