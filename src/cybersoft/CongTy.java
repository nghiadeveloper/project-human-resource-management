package cybersoft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CongTy {

	private String tenCongTy;
	private String maSoThue;
	private double doanhThuThang;
	private List<NhanVien> danhSachNV;

	public CongTy(String tenCongTy, String maSoThue, double doanhThuThang) {
		this.tenCongTy = tenCongTy;
		this.maSoThue = maSoThue;
		this.doanhThuThang = doanhThuThang;
		this.danhSachNV = new ArrayList<>();
	}

	public void themNhanVien(NhanVien nv) {
		danhSachNV.add(nv);
	}

	public void xoaNhanVien(int maSo) {
		NhanVien nhanVien = timNhanVien(maSo);
		if (nhanVien != null) {
			if (nhanVien instanceof TruongPhong) {
				TruongPhong truongPhong = (TruongPhong) nhanVien;
				for (NhanVienThuong nvThuong : truongPhong.getDanhSachNhanVien()) {
					nvThuong.setTruongPhongHienTai(null);
				}
			}
			danhSachNV.remove(nhanVien);
			System.out.println("Nhân sự đã được xóa");
		} else {
			System.out.println("Không tìm thấy nhân sự với mã số này.");
		}
	}

	public void xoaNhanVienKhoiTruongPhong(int maSoNV) {
		NhanVien nv = timNhanVien(maSoNV);
		if (nv instanceof NhanVienThuong) {
			NhanVienThuong nvThuong = (NhanVienThuong) nv;
			TruongPhong tp = nvThuong.getTruongPhongHienTai();

			if (tp != null) {
				tp.xoaNhanVien(nvThuong);
				System.out.println("Nhân viên đã được xóa khỏi danh sách nshân viên dưới quyền của trưởng phòng.");
			} else {
				System.out.println("Nhân viên không có trưởng phòng.");
			}
		}
	}

	public NhanVien timNhanVien(int maSo) {
		for (NhanVien nv : danhSachNV) {
			if (nv.getMaSo() == maSo) {
				return nv;
			}
		}
		return null;
	}

	public void xuatThongTin() {
		for (NhanVien nv : danhSachNV) {
			if (nv instanceof GiamDoc) {
				GiamDoc gd = (GiamDoc) nv;
				System.out.println("Giám đốc: Mã số: " + gd.getMaSo() 
						+ " - Họ và tên: " + gd.getHoTen()
						+ " - Số điện thoại: " + gd.getSoDienThoai() 
						+ " - Lương một ngày: " + gd.getLuongMotNgay()
						+ " - Số ngày làm việc: " + gd.getSoNgayLamViec() 
						+ " - Cổ phần: " + gd.getCoPhan()
						+ " - Lương tháng: " + gd.tinhLuongThang());
			} else if (nv instanceof TruongPhong) {
				TruongPhong tp = (TruongPhong) nv;
				System.out.println("Trưởng phòng: Mã số: " + tp.getMaSo() 
						+ " - Họ và tên: " + tp.getHoTen()
						+ " - Số điện thoại: " + tp.getSoDienThoai() 
						+ " - Lương một ngày: " + tp.getLuongMotNgay()
						+ " - Số ngày làm việc: " + tp.getSoNgayLamViec() 
						+ " - Lương tháng: " + tp.tinhLuongThang());
			} else {
				System.out.println("Nhán viên: Mã số: " + nv.getMaSo() 
						+ " - Họ và tên: " + nv.getHoTen()
						+ " - Số điện thoại: " + nv.getSoDienThoai() 
						+ " - Lương một ngày: " + nv.getLuongMotNgay()
						+ " - Số ngày làm việc: " + nv.getSoNgayLamViec() 
						+ "- Lương tháng: " + nv.tinhLuongThang());
			}
		}
	}

	public double tinhTongLuongThang() {
		double tongLuong = 0;
		for (NhanVien nv : danhSachNV) {
			tongLuong += nv.tinhLuongThang();
		}
		return tongLuong;
	}

	public NhanVienThuong timNhanVienThuongLuongCaoNhat() {
		NhanVienThuong nvLuongCaoNhat = null;
		for (NhanVien nv : danhSachNV) {
			if (nv instanceof NhanVienThuong) {
				if (nvLuongCaoNhat == null || nv.tinhLuongThang() > nvLuongCaoNhat.tinhLuongThang()) {
					nvLuongCaoNhat = (NhanVienThuong) nv;
				}
			}
		}
		return nvLuongCaoNhat;
	}

	public TruongPhong timTruongPhongCoNhieuNhanVienNhat() {
		TruongPhong tpMax = null;
		int maxSoNhanVien = 0;

		for (NhanVien nv : danhSachNV) {
			if (nv instanceof TruongPhong) {
				TruongPhong tp = (TruongPhong) nv;
				int soNhanVien = tp.getDanhSachNhanVien().size();
				if (soNhanVien > maxSoNhanVien) {
					maxSoNhanVien = soNhanVien;
					tpMax = tp;
				}
			}
		}
		return tpMax;
	}

	public void sapXepNhanVienTheoTenCuoi() {
		Collections.sort(danhSachNV, new Comparator<NhanVien>() {
			@Override
			public int compare(NhanVien nv1, NhanVien nv2) {
				char lastChar1 = nv1.getHoTen().charAt(nv1.getHoTen().length() - 1);
				char lastChar2 = nv2.getHoTen().charAt(nv2.getHoTen().length() - 1);
				return Character.compare(lastChar1, lastChar2);
			}
		});
	}

	public void sapXepNhanVienTheoLuongGiamDan() {
		Collections.sort(danhSachNV, new Comparator<NhanVien>() {
			@Override
			public int compare(NhanVien nv1, NhanVien nv2) {
				return Double.compare(nv2.tinhLuongThang(), nv1.tinhLuongThang());
			}
		});
	}

	public GiamDoc timGiamDocCoCoPhanNhieuNhat() {
		GiamDoc gdMax = null;
		for (NhanVien nv : danhSachNV) {
			if (nv instanceof GiamDoc) {
				GiamDoc gd = (GiamDoc) nv;
				if (gdMax == null || gd.getCoPhan() > gdMax.getCoPhan()) {
					gdMax = gd;
				}
			}
		}
		return gdMax;
	}

	public void tinhVaXuatThuNhapGiamDoc() {
		double tongLuongThang = tinhTongLuongThang();
		double loiNhuanCongTy = doanhThuThang - tongLuongThang;

		for (NhanVien nv : danhSachNV) {
			if (nv instanceof GiamDoc) {
				GiamDoc gd = (GiamDoc) nv;
				double thuNhap = gd.tinhThuNhap(loiNhuanCongTy);
				System.out.println("Giám đốc: " + gd.getHoTen() + " - Tổng thu nhập: " + thuNhap);
			}
		}
	}

	public List<NhanVien> getDanhSachNV() {
		return danhSachNV;
	}

	public void setDanhSachNV(List<NhanVien> danhSachNV) {
		this.danhSachNV = danhSachNV;
	}

	public void xuatThongTinCongTy() {
		System.out.println("\nCông ty " + this.tenCongTy);
		System.out.println("Mã số thuế: " + this.maSoThue);
		System.out.println("Doanh thu tháng: " + this.doanhThuThang);
	}

}
