package cybersoft;

import java.util.ArrayList;
import java.util.List;

public class TruongPhong extends NhanVien {

	private List<NhanVienThuong> danhSachNhanVien;

	public TruongPhong(int maSo, String hoTen, String soDienThoai, int soNgayLamViec, double luongMotNgay) {
		super(maSo, hoTen, soDienThoai, soNgayLamViec, luongMotNgay);
		this.danhSachNhanVien = new ArrayList<>();
	}

	public void themNhanVien(NhanVienThuong nv) {
		danhSachNhanVien.add(nv);
		nv.setTruongPhongHienTai(this);
	}

	public void xoaNhanVien(NhanVienThuong nv) {
		danhSachNhanVien.remove(nv);
		nv.setTruongPhongHienTai(null);
	}

	public ArrayList<NhanVienThuong> getDanhSachNhanVien() {
		return (ArrayList<NhanVienThuong>) danhSachNhanVien;
	}

	@Override
	public double tinhLuongThang() {
		return this.getLuongMotNgay() * this.getSoNgayLamViec();
	}

}
