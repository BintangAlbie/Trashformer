package com.example.trashformer.specification;

import com.example.trashformer.dto.request.LaporanFilterRequest;
import com.example.trashformer.entity.SetoranSampah;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class SetoranSpecifications {

    private SetoranSpecifications() {
    }

    public static Specification<SetoranSampah> withFilter(LaporanFilterRequest filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter == null) {
                return criteriaBuilder.conjunction();
            }

            if (filter.tanggalAwal() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("tanggalSetoran"), filter.tanggalAwal()));
            }
            if (filter.tanggalAkhir() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("tanggalSetoran"), filter.tanggalAkhir()));
            }
            if (filter.wargaId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("warga").get("id"), filter.wargaId()));
            }
            if (filter.petugasId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("petugas").get("id"), filter.petugasId()));
            }
            if (filter.tipeSampahId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("tipeSampah").get("id"), filter.tipeSampahId()));
            }
            if (filter.status() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.status()));
            }
            if (filter.role() != null) {
                Predicate wargaRole = criteriaBuilder.equal(root.get("warga").get("user").get("role"), filter.role());
                Predicate petugasRole = criteriaBuilder.equal(root.get("petugas").get("user").get("role"), filter.role());
                predicates.add(criteriaBuilder.or(wargaRole, petugasRole));
            }

            if (query != null) {
                query.orderBy(
                        criteriaBuilder.desc(root.get("tanggalSetoran")),
                        criteriaBuilder.desc(root.get("id"))
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
