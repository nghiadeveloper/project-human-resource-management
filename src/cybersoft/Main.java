package cybersoft;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		CongTy congTy = null;
		while (true) {
			menu();
			System.out.print("Lựa chọn: ");
			int luaChon = scanner.nextInt();
			scanner.nextLine();
			switch (luaChon) {
			case 1:
				System.out.print("Nhập tên công ty: ");
				String tenCongTy = scanner.nextLine();
				System.out.print("Nhập mã số thuế: ");
				String maSoThue = scanner.nextLine();
				System.out.print("Nhập doanh thu tháng: ");
				double doanhThuThang = scanner.nextDouble();
				scanner.nextLine();
				congTy = new CongTy(tenCongTy, maSoThue, doanhThuThang);

				// Giám đốc
				GiamDoc giamDoc = new GiamDoc(1, "Nguyễn Thúc Thùy Tiên", "0123456789", 25, 300, 5);
				GiamDoc giamDoc1 = new GiamDoc(2, "Phạm Quang Linh", "0135792468", 28, 300, 9);
				congTy.themNhanVien(giamDoc);
				congTy.themNhanVien(giamDoc1);

				// Trưởng phòng
				TruongPhong truongPhong = new TruongPhong(4, "Phạm Thị Nhật Lệ", "0911111111", 30, 200);
				TruongPhong truongPhong1 = new TruongPhong(3, "Nam Hoàng", "0922222222", 20, 200);
				congTy.themNhanVien(truongPhong);
				congTy.themNhanVien(truongPhong1);

				// Nhân viên
				NhanVienThuong nhanVien = new NhanVienThuong(5, "Tiến Nguyễn", "0933333333", 20, 100);
				NhanVienThuong nhanVien1 = new NhanVienThuong(6, "Lôi Con", "0944444444", 22, 100);
				NhanVienThuong nhanVien2 = new NhanVienThuong(7, "Hằng Du Mục", "0955555555", 28, 100);
				NhanVienThuong nhanVien3 = new NhanVienThuong(8, "Lê Bảo Bình", "0966666666", 25, 100);
				congTy.themNhanVien(nhanVien);
				congTy.themNhanVien(nhanVien1);
				congTy.themNhanVien(nhanVien2);
				congTy.themNhanVien(nhanVien3);

				// Nhân viên dưới quyền
				truongPhong.themNhanVien(nhanVien);
				truongPhong.themNhanVien(nhanVien1);

				System.out.println("Thông tin công ty và dữ liệu nhân viên đã được nhập thành công.");
				break;
			case 2:
				if (congTy != null) {
					System.out.print("Nhập mã trưởng phòng: ");
					int maSoTP = scanner.nextInt();
					scanner.nextLine();
					TruongPhong tp1 = (TruongPhong) congTy.timNhanVien(maSoTP);

					if (tp1 != null) {
						System.out.print("Nhập mã nhân viên: ");
						int maSoNV = scanner.nextInt();
						scanner.nextLine();
						NhanVien nv = congTy.timNhanVien(maSoNV);

						if (nv != null && nv instanceof NhanVienThuong) {
							NhanVienThuong nvThuong = (NhanVienThuong) nv;
							if (nvThuong.getTruongPhongHienTai() == null) {
								tp1.themNhanVien(nvThuong);
								nvThuong.setTruongPhongHienTai(tp1);
								System.out.println("Nhân viên đã được phân bổ vào trưởng phòng.");
							} else {
								System.out.println("Nhân viên đã được phân bổ vào trưởng phòng khác.");
							}
						} else {
							System.out.println("Không tìm thấy nhân viên với mã số này.");
						}
					} else {
						System.out.println("Không tìm thấy trưởng phòng với mã số này.");
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 3:
				NhanVien nv = null;
				boolean kiemTra = true;
				if (congTy != null) {
					while (kiemTra) {
						System.out.println("\n----------MENU----------");
						System.out.println("1. Thêm nhân sự");
						System.out.println("2. Xóa nhân sự");
						System.out.println("0. Thoát");
						System.out.print("Lựa chọn: ");
						luaChon = scanner.nextInt();
						switch (luaChon) {
						case 0:
							kiemTra = false;
							break;
						case 1:
							System.out.println("\n----------MENU----------");
							System.out.println("1. Thêm giám đốc");
							System.out.println("2. Thêm Trưởng phòng");
							System.out.println("3. Thêm nhân viên thường");
							System.out.print("Lựa chọn: ");
							luaChon = scanner.nextInt();

							switch (luaChon) {
							case 1:
								double coPhan;
								System.out.print("Nhập mã nhân viên: ");
								int maSoGiamDoc = scanner.nextInt();
								scanner.nextLine();

								System.out.print("Nhập họ tên: ");
								String hoTenGiamDoc = scanner.nextLine();

								System.out.print("Nhập số điện thoại: ");
								String soDienThoaiGiamDoc = scanner.nextLine();

								System.out.print("Nhập số ngày làm việc: ");
								int soNgayLamViecGiamDoc = scanner.nextInt();
								scanner.nextLine();

								do {
									System.out.print("Nhập cổ phần (%): ");
									coPhan = scanner.nextDouble();
									if (coPhan <= 0 || coPhan > 100) {
										System.out.println("Cổ phần phải lớn hơn 0 và nhỏ hơn 100.");
									}
								} while (coPhan <= 0 || coPhan > 100);

								nv = new GiamDoc(maSoGiamDoc, hoTenGiamDoc, soDienThoaiGiamDoc, soNgayLamViecGiamDoc,
										300, coPhan);
								congTy.themNhanVien(nv);
								System.out.println("Nhân sự đã được thêm.");
								break;

							case 2:
								System.out.print("Nhập mã nhân viên: ");
								int maSoTruongPhong = scanner.nextInt();
								scanner.nextLine();

								System.out.print("Nhập họ tên: ");
								String hoTenTruongPhong = scanner.nextLine();

								System.out.print("Nhập số điện thoại: ");
								String soDienThoaiTruongPhong = scanner.nextLine();

								System.out.print("Nhập số ngày làm việc: ");
								int soNgayLamViecTruongPhong = scanner.nextInt();
								scanner.nextLine();

								nv = new TruongPhong(maSoTruongPhong, hoTenTruongPhong, soDienThoaiTruongPhong,
										soNgayLamViecTruongPhong, 200);
								congTy.themNhanVien(nv);
								System.out.println("Nhân sự đã được thêm.");
								break;

							case 3:
								System.out.print("Nhập mã nhân viên: ");
								int maSoNV = scanner.nextInt();
								scanner.nextLine();

								System.out.print("Nhập họ tên: ");
								String hoTenNV = scanner.nextLine();

								System.out.print("Nhập số điện thoại: ");
								String soDienThoaiNV = scanner.nextLine();

								System.out.print("Nhập số ngày làm việc: ");
								int soNgayLamViecNV = scanner.nextInt();
								scanner.nextLine();

								nv = new NhanVienThuong(maSoNV, hoTenNV, soDienThoaiNV, soNgayLamViecNV, 100);
								congTy.themNhanVien(nv);
								System.out.println("Nhân sự đã được thêm.");
								break;
							}
							break;
						case 2:
							System.out.print("Nhập mã nhân sự cần xóa: ");
							int maSoNV = scanner.nextInt();
							scanner.nextLine();
							congTy.xoaNhanVien(maSoNV);
							scanner.nextLine();
							congTy.xoaNhanVienKhoiTruongPhong(maSoNV);
							break;
						default:
							System.out.println("Lựa chọn không hợp lệ! Vui lòng thử lại.");
							break;
						}
					}
					break;
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 4:
				congTy.xuatThongTinCongTy();
				if (congTy != null) {
					congTy.xuatThongTin();
					System.out.println("\nThông tin chi tiết về các nhân viên thuộc trưởng phòng:");
					for (NhanVien nhanVienThuocTruongPhong : congTy.getDanhSachNV()) {
						if (nhanVienThuocTruongPhong instanceof NhanVienThuong) {
							NhanVienThuong nvThuong = (NhanVienThuong) nhanVienThuocTruongPhong;
							TruongPhong truongPhong3 = nvThuong.getTruongPhongHienTai();
							if (truongPhong3 != null) {
								System.out.println("Nhân viên - Mã số: " + nvThuong.getMaSo() + " - Họ và tên: "
										+ nvThuong.getHoTen() + " Thuộc trưởng phòng - Mã số: " + truongPhong3.getMaSo()
										+ "Họ và tên: " + truongPhong3.getHoTen());
							} else {
								System.out.println("Nhân viên - Mã số: " + nvThuong.getMaSo() + " - Họ và tên: "
										+ nvThuong.getHoTen() + " chưa được phân bổ vào trưởng phòng nào");
							}
						}
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 5:
				congTy.xuatThongTinCongTy();
				if (congTy != null) {
					double tongLuong = congTy.tinhTongLuongThang();
					System.out.println("Tổng lương toàn nhân viên công ty: " + tongLuong);
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 6:
				if (congTy != null) {
					NhanVienThuong nvLuongCaoNhat = congTy.timNhanVienThuongLuongCaoNhat();
					if (nvLuongCaoNhat != null) {
						System.out.println("Nhân viên có lương cao nhất: ");
						System.out.println("Mã số: " + nvLuongCaoNhat.getMaSo());
						System.out.println("Họ tên: " + nvLuongCaoNhat.getHoTen());
						System.out.println("Số điện thoại: " + nvLuongCaoNhat.getSoDienThoai());
						System.out.println("Số ngày làm việc: " + nvLuongCaoNhat.getSoNgayLamViec());
						System.out.println("Lương một ngày: " + nvLuongCaoNhat.getLuongMotNgay());
						System.out.println("Lương tháng: " + nvLuongCaoNhat.tinhLuongThang());
					} else {
						System.out.println("Không có nhân viên thường trong công ty.");
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 7:
				if (congTy != null) {
					TruongPhong tpMax = congTy.timTruongPhongCoNhieuNhanVienNhat();
					if (tpMax != null) {
						System.out.println("Trưởng phòng có nhiều nhân viên nhất: " + tpMax.getHoTen());
						System.out.println("Số lượng nhân viên dưới quyền: " + tpMax.getDanhSachNhanVien().size());
					} else {
						System.out.println("Công ty không có trưởng phòng nào.");
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 8:
				congTy.xuatThongTinCongTy();
				if (congTy != null) {
					congTy.sapXepNhanVienTheoTenCuoi();
					System.out.println("Tên nhân viên được sắp xếp theo thứ tự abc.");
					congTy.xuatThongTin();
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 9:
				congTy.xuatThongTinCongTy();
				if (congTy != null) {
					congTy.sapXepNhanVienTheoLuongGiamDan();
					System.out.println("Nhân viên đã được sắp xếp theo thứ tự lương giảm dần.");
					congTy.xuatThongTin();
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 10:
				if (congTy != null) {
					GiamDoc gdMax = congTy.timGiamDocCoCoPhanNhieuNhat();
					if (gdMax != null) {
						System.out.println("Giám đốc có cổ phần nhiều nhất: " + gdMax);
						System.out.println("Số cổ phần: " + gdMax.getCoPhan() + "%");
					} else {
						System.out.println("Công ty không có giám đốc nào.");
					}
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 11:
				if (congTy != null) {
					congTy.tinhVaXuatThuNhapGiamDoc();
				} else {
					System.out.println("Vui lòng nhập thông tin công ty.");
				}
				break;

			case 0:
				System.out.println("Đã dừng chương trình quản lý nhân sự.");
				scanner.close();
				return;
			default:
				System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
			}
		}

	}

	public static void menu() {
		System.out.println("\n------------------------- MENU -------------------------");
		System.out.println("1. Nhập thông tin công ty");
		System.out.println("2. Phân bổ nhân viên vào trưởng phòng");
		System.out.println("3. Thêm, xóa nhân sự");
		System.out.println("4. Xuất thông tin toàn bộ người trong công ty");
		System.out.println("5. Tính và xuất tổng lương cho toàn công ty");
		System.out.println("6. Tìm nhân viên thường có lương cao nhất");
		System.out.println("7. Tìm trưởng phòng có số lượng nhân viên dưới quyền nhiều nhất");
		System.out.println("8. Sắp xếp nhân viên toàn công ty theo thứ tự abc");
		System.out.println("9. Sắp xếp nhân viên toàn công ty theo thứ tự lương giảm dần");
		System.out.println("10. Tìm giám đốc có số lượng cổ phần nhiều nhất");
		System.out.println("11. Tính và xuất tổng thu nhập của từng giám đốc");
		System.out.println("0. Thoát");
	}

}
