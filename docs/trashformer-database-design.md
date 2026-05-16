# TRASHFORMER Database Design

## ERD

```mermaid
erDiagram
    USERS ||--o| WARGA : "has profile"
    USERS ||--o| PETUGAS : "has profile"
    WARGA ||--o{ SETORAN_SAMPAH : "owns"
    PETUGAS ||--o{ SETORAN_SAMPAH : "verifies"
    TIPE_SAMPAH ||--o{ SETORAN_SAMPAH : "classifies"
    WARGA ||--o{ LAPORAN_SAMPAH : "submits"
    PETUGAS ||--o{ LAPORAN_SAMPAH : "handles"
    TIPE_SAMPAH ||--o{ LAPORAN_SAMPAH : "categorizes"
    LAPORAN_SAMPAH ||--o| HASIL_LAPORAN : "resolved by"
    PETUGAS ||--o{ HASIL_LAPORAN : "writes"

    USERS {
        bigint id PK
        varchar nama
        varchar email UK
        varchar password
        enum role
        timestamp created_at
        timestamp updated_at
    }

    WARGA {
        bigint id PK
        bigint user_id FK UK
        varchar nik UK
        varchar alamat
        varchar no_hp
        varchar rt
        varchar rw
        timestamp created_at
        timestamp updated_at
    }

    PETUGAS {
        bigint id PK
        bigint user_id FK UK
        varchar nomor_petugas UK
        varchar no_hp
        varchar alamat
        timestamp created_at
        timestamp updated_at
    }

    TIPE_SAMPAH {
        bigint id PK
        varchar nama_tipe UK
        varchar deskripsi
        decimal harga_per_kg
        timestamp created_at
        timestamp updated_at
    }

    SETORAN_SAMPAH {
        bigint id PK
        bigint warga_id FK
        bigint petugas_id FK
        bigint tipe_sampah_id FK
        decimal berat_kg
        date tanggal_setoran
        enum status
        varchar catatan
        timestamp created_at
        timestamp updated_at
    }

    LAPORAN_SAMPAH {
        bigint id PK
        bigint warga_id FK
        bigint petugas_id FK
        bigint tipe_sampah_id FK
        varchar judul_laporan
        text deskripsi
        varchar lokasi
        date tanggal_laporan
        enum status_laporan
        varchar foto_url
        timestamp created_at
        timestamp updated_at
    }

    HASIL_LAPORAN {
        bigint id PK
        bigint laporan_sampah_id FK UK
        bigint petugas_id FK
        text keterangan_hasil
        date tanggal_selesai
        varchar file_pdf_url
        timestamp created_at
        timestamp updated_at
    }
}
```

## Tabel dan Atribut

### `users`

| Kolom | Tipe | Keterangan |
|---|---|---|
| `id` | `BIGINT` | Primary key |
| `nama` | `VARCHAR(120)` | Nama lengkap user |
| `email` | `VARCHAR(120)` | Unik untuk login |
| `password` | `VARCHAR(255)` | Password terenkripsi BCrypt |
| `role` | `VARCHAR(20)` | `ADMIN`, `PETUGAS`, `WARGA` |
| `created_at` | `TIMESTAMP` | Waktu dibuat |
| `updated_at` | `TIMESTAMP` | Waktu diubah |

### `warga`

| Kolom | Tipe | Keterangan |
|---|---|---|
| `id` | `BIGINT` | Primary key |
| `user_id` | `BIGINT` | FK ke `users.id`, unik |
| `nik` | `VARCHAR(32)` | Nomor induk kependudukan |
| `alamat` | `VARCHAR(255)` | Alamat rumah |
| `no_hp` | `VARCHAR(30)` | Nomor HP |
| `rt` | `VARCHAR(10)` | RT |
| `rw` | `VARCHAR(10)` | RW |
| `created_at` | `TIMESTAMP` | Waktu dibuat |
| `updated_at` | `TIMESTAMP` | Waktu diubah |

### `petugas`

| Kolom | Tipe | Keterangan |
|---|---|---|
| `id` | `BIGINT` | Primary key |
| `user_id` | `BIGINT` | FK ke `users.id`, unik |
| `nomor_petugas` | `VARCHAR(30)` | Kode petugas |
| `no_hp` | `VARCHAR(30)` | Nomor HP |
| `alamat` | `VARCHAR(255)` | Alamat petugas |
| `created_at` | `TIMESTAMP` | Waktu dibuat |
| `updated_at` | `TIMESTAMP` | Waktu diubah |

### `tipe_sampah`

| Kolom | Tipe | Keterangan |
|---|---|---|
| `id` | `BIGINT` | Primary key |
| `nama_tipe` | `VARCHAR(100)` | Nama tipe sampah |
| `deskripsi` | `VARCHAR(255)` | Penjelasan tipe |
| `harga_per_kg` | `DECIMAL(12,2)` | Nilai ekonomi per kg |
| `created_at` | `TIMESTAMP` | Waktu dibuat |
| `updated_at` | `TIMESTAMP` | Waktu diubah |

### `setoran_sampah`

| Kolom | Tipe | Keterangan |
|---|---|---|
| `id` | `BIGINT` | Primary key |
| `warga_id` | `BIGINT` | FK ke `warga.id` |
| `petugas_id` | `BIGINT` | FK ke `petugas.id` |
| `tipe_sampah_id` | `BIGINT` | FK ke `tipe_sampah.id` |
| `berat_kg` | `DECIMAL(10,2)` | Berat sampah |
| `tanggal_setoran` | `DATE` | Tanggal transaksi |
| `status` | `VARCHAR(30)` | Status setoran |
| `catatan` | `VARCHAR(500)` | Catatan verifikasi |
| `created_at` | `TIMESTAMP` | Waktu dibuat |
| `updated_at` | `TIMESTAMP` | Waktu diubah |

### `laporan_sampah`

| Kolom | Tipe | Keterangan |
|---|---|---|
| `id` | `BIGINT` | Primary key |
| `warga_id` | `BIGINT` | FK ke `warga.id` |
| `petugas_id` | `BIGINT` | FK ke `petugas.id`, nullable sebelum assignment |
| `tipe_sampah_id` | `BIGINT` | FK ke `tipe_sampah.id`, nullable |
| `judul_laporan` | `VARCHAR(150)` | Judul laporan warga |
| `deskripsi` | `TEXT` | Deskripsi laporan |
| `lokasi` | `VARCHAR(255)` | Lokasi sampah |
| `tanggal_laporan` | `DATE` | Tanggal laporan |
| `status_laporan` | `VARCHAR(30)` | Status proses laporan |
| `foto_url` | `VARCHAR(255)` | Foto pendukung |
| `created_at` | `TIMESTAMP` | Waktu dibuat |
| `updated_at` | `TIMESTAMP` | Waktu diubah |

### `hasil_laporan`

| Kolom | Tipe | Keterangan |
|---|---|---|
| `id` | `BIGINT` | Primary key |
| `laporan_sampah_id` | `BIGINT` | FK ke `laporan_sampah.id`, unik |
| `petugas_id` | `BIGINT` | FK ke `petugas.id` |
| `keterangan_hasil` | `TEXT` | Ringkasan penanganan |
| `tanggal_selesai` | `DATE` | Tanggal selesai |
| `file_pdf_url` | `VARCHAR(255)` | Lokasi file PDF hasil |
| `created_at` | `TIMESTAMP` | Waktu dibuat |
| `updated_at` | `TIMESTAMP` | Waktu diubah |

## Relasi

1. `users` 1:1 `warga`
2. `users` 1:1 `petugas`
3. `warga` 1:N `setoran_sampah`
4. `petugas` 1:N `setoran_sampah`
5. `tipe_sampah` 1:N `setoran_sampah`
6. `warga` 1:N `laporan_sampah`
7. `petugas` 1:N `laporan_sampah`
8. `tipe_sampah` 1:N `laporan_sampah`
9. `laporan_sampah` 1:1 `hasil_laporan`
10. `petugas` 1:N `hasil_laporan`

## Aturan Bisnis

1. Hanya boleh ada satu akun `ADMIN` utama.
2. `WARGA` dan `PETUGAS` selalu dibuat melalui profile masing-masing agar selalu terhubung dengan `users`.
3. Setiap setoran wajib mencatat warga, petugas, tipe sampah, berat, tanggal, dan status.
4. Laporan setoran bisa difilter per tanggal, warga, petugas, tipe sampah, status, dan role user.
5. Ekspor laporan mendukung format PDF dan HTML print-ready.

## Contoh Query Laporan

### Laporan harian

```sql
SELECT
    tanggal_setoran,
    SUM(berat_kg) AS total_berat_kg,
    COUNT(id) AS jumlah_setoran
FROM setoran_sampah
WHERE tanggal_setoran BETWEEN :tanggalAwal AND :tanggalAkhir
GROUP BY tanggal_setoran
ORDER BY tanggal_setoran;
```

### Laporan bulanan

```sql
SELECT
    EXTRACT(YEAR FROM tanggal_setoran) AS tahun,
    EXTRACT(MONTH FROM tanggal_setoran) AS bulan,
    SUM(berat_kg) AS total_berat_kg,
    COUNT(id) AS jumlah_setoran
FROM setoran_sampah
WHERE tanggal_setoran BETWEEN :tanggalAwal AND :tanggalAkhir
GROUP BY EXTRACT(YEAR FROM tanggal_setoran), EXTRACT(MONTH FROM tanggal_setoran)
ORDER BY tahun, bulan;
```

## Dashboard

### ADMIN

- Total user per role
- Total setoran hari ini, bulan ini, tahun ini
- Status setoran pending, diterima, ditolak, selesai
- Top tipe sampah
- Ringkasan performa petugas dan aktivitas laporan

### PETUGAS

- Setoran yang diverifikasi hari ini
- Total berat yang ditangani
- Laporan warga yang perlu ditindaklanjuti
- Riwayat setoran terbaru

### WARGA

- Total setoran pribadi
- Total berat sampah pribadi
- Status setoran pribadi
- Riwayat laporan dan setoran terbaru
