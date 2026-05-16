package com.example.trashformer.util;

import com.example.trashformer.dto.response.HasilLaporanResponse;
import com.example.trashformer.dto.response.LaporanSampahResponse;
import com.example.trashformer.dto.response.PembayaranSampahResponse;
import com.example.trashformer.dto.response.PetugasResponse;
import com.example.trashformer.dto.response.SetoranSampahResponse;
import com.example.trashformer.dto.response.TipeSampahResponse;
import com.example.trashformer.dto.response.UserResponse;
import com.example.trashformer.dto.response.WargaResponse;
import com.example.trashformer.entity.HasilLaporan;
import com.example.trashformer.entity.LaporanSampah;
import com.example.trashformer.entity.PembayaranSampah;
import com.example.trashformer.entity.Petugas;
import com.example.trashformer.entity.SetoranSampah;
import com.example.trashformer.entity.TipeSampah;
import com.example.trashformer.entity.User;
import com.example.trashformer.entity.Warga;

public final class DtoMapper {

    private DtoMapper() {
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getNama(),
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static WargaResponse toWargaResponse(Warga warga) {
        return new WargaResponse(
                warga.getId(),
                warga.getUser().getId(),
                warga.getUser().getNama(),
                warga.getUser().getEmail(),
                warga.getNik(),
                warga.getAlamat(),
                warga.getNoHp(),
                warga.getRt(),
                warga.getRw(),
                warga.getCreatedAt(),
                warga.getUpdatedAt()
        );
    }

    public static PetugasResponse toPetugasResponse(Petugas petugas) {
        return new PetugasResponse(
                petugas.getId(),
                petugas.getUser().getId(),
                petugas.getUser().getNama(),
                petugas.getUser().getEmail(),
                petugas.getNomorPetugas(),
                petugas.getAlamat(),
                petugas.getNoHp(),
                petugas.getCreatedAt(),
                petugas.getUpdatedAt()
        );
    }

    public static TipeSampahResponse toTipeSampahResponse(TipeSampah tipeSampah) {
        return new TipeSampahResponse(
                tipeSampah.getId(),
                tipeSampah.getNamaTipe(),
                tipeSampah.getDeskripsi(),
                tipeSampah.getHargaPerKg(),
                tipeSampah.getCreatedAt(),
                tipeSampah.getUpdatedAt()
        );
    }

    public static SetoranSampahResponse toSetoranSampahResponse(SetoranSampah setoranSampah) {
        return new SetoranSampahResponse(
                setoranSampah.getId(),
                setoranSampah.getWarga().getId(),
                setoranSampah.getWarga().getUser().getNama(),
                setoranSampah.getPetugas().getId(),
                setoranSampah.getPetugas().getUser().getNama(),
                setoranSampah.getTipeSampah().getId(),
                setoranSampah.getTipeSampah().getNamaTipe(),
                setoranSampah.getBeratKg(),
                setoranSampah.getTanggalSetoran(),
                setoranSampah.getStatus(),
                setoranSampah.getCatatan(),
                setoranSampah.getCreatedAt(),
                setoranSampah.getUpdatedAt()
        );
    }

    public static HasilLaporanResponse toHasilLaporanResponse(HasilLaporan hasilLaporan) {
        return new HasilLaporanResponse(
                hasilLaporan.getId(),
                hasilLaporan.getLaporanSampah().getId(),
                hasilLaporan.getPetugas().getId(),
                hasilLaporan.getPetugas().getUser().getNama(),
                hasilLaporan.getKeteranganHasil(),
                hasilLaporan.getTanggalSelesai(),
                hasilLaporan.getFilePdfUrl(),
                hasilLaporan.getCreatedAt(),
                hasilLaporan.getUpdatedAt()
        );
    }

    public static LaporanSampahResponse toLaporanSampahResponse(LaporanSampah laporanSampah) {
        return new LaporanSampahResponse(
                laporanSampah.getId(),
                laporanSampah.getWarga().getId(),
                laporanSampah.getWarga().getUser().getNama(),
                laporanSampah.getPetugas() != null ? laporanSampah.getPetugas().getId() : null,
                laporanSampah.getPetugas() != null ? laporanSampah.getPetugas().getUser().getNama() : null,
                laporanSampah.getTipeSampah() != null ? laporanSampah.getTipeSampah().getId() : null,
                laporanSampah.getTipeSampah() != null ? laporanSampah.getTipeSampah().getNamaTipe() : null,
                laporanSampah.getJudulLaporan(),
                laporanSampah.getDeskripsi(),
                laporanSampah.getLokasi(),
                laporanSampah.getTanggalLaporan(),
                laporanSampah.getStatusLaporan(),
                laporanSampah.getFotoUrl(),
                laporanSampah.getHasilLaporan() != null ? toHasilLaporanResponse(laporanSampah.getHasilLaporan()) : null,
                laporanSampah.getCreatedAt(),
                laporanSampah.getUpdatedAt()
        );
    }

    public static PembayaranSampahResponse toPembayaranSampahResponse(PembayaranSampah pembayaranSampah) {
        return new PembayaranSampahResponse(
                pembayaranSampah.getId(),
                pembayaranSampah.getWarga().getId(),
                pembayaranSampah.getWarga().getUser().getNama(),
                pembayaranSampah.getPeriodeTagihan(),
                pembayaranSampah.getNominal(),
                pembayaranSampah.getTanggalPembayaran(),
                pembayaranSampah.getMetodePembayaran(),
                pembayaranSampah.getStatus(),
                pembayaranSampah.getKeterangan(),
                pembayaranSampah.getCatatanVerifikasi(),
                pembayaranSampah.getBuktiPembayaranUrl(),
                pembayaranSampah.getCreatedAt(),
                pembayaranSampah.getUpdatedAt()
        );
    }
}
