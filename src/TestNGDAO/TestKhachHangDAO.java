package TestNGDAO;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ShopQuanLyAoQuan.dao.KhachHangDAO;
import ShopQuanLyAoQuan.entity.KhachHang;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;

public class TestKhachHangDAO {

    KhachHangDAO khachHangDAO;

    @BeforeMethod
    public void setUp() {
        khachHangDAO = new KhachHangDAO();
    }

    @AfterMethod
    public void tearDown() {
        khachHangDAO = null;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test(description = "Kiểm thử chèn với mô hình null")
    public void testInsertWithNullModel() {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH(null);
        exceptionRule.expect(Exception.class);
        khachHangDAO.insert(khachHang);
    }
    @Rule
    public ExpectedException testInsertWithEmptyModel = ExpectedException.none();
    @Test(description = "Kiểm thử chèn với mô hình trống")
    public void testInsertWithEmptyModel() {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH("");
        exceptionRule.expect(Exception.class);
        khachHangDAO.insert(khachHang);
    }

    @Test(description = "Kiểm thử chèn với mô hình hợp lệ")
    public void testInsertWithValidModel() {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH("KH00012");
        khachHang.setHoTen("Nguyễn Anna");
        khachHang.setDiaChi("Quãng Nam");
        khachHang.setSDT("0987344001");
        khachHang.setEmail("nguyenanna@gmail.com");
        khachHang.setGhiChu(null);
        try {
            khachHangDAO.insert(khachHang);
        } catch (Exception e) {
            Assert.fail("Chèn với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử cập nhật với mô hình null")
    public void testUpdateWithNullModel() {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH(null);
        exceptionRule.expect(Exception.class);
        khachHangDAO.update(khachHang);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình trống")
    public void testUpdateWithEmptyModel() {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH(" ");
        exceptionRule.expect(Exception.class);
        khachHangDAO.update(khachHang);
    }

    @Test(description = "Kiểm thử cập nhật với mô hình hợp lệ")
    public void testUpdateWithValidModel() {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH("KH03");
        khachHang.setHoTen("Nguyễn Văn C");
        try {
            khachHangDAO.update(khachHang);
        } catch (Exception e) {
            Assert.fail("Cập nhật với mô hình hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử xóa với ID null")
    public void testDeleteWithNullModel() {
        exceptionRule.expect(Exception.class);
        khachHangDAO.delete(null);
    }

    @Test(description = "Kiểm thử xóa với ID trống")
    public void testDeleteWithEmptyModel() {
        exceptionRule.expect(Exception.class);
        khachHangDAO.delete(" ");
    }

    @Test(description = "Kiểm thử xóa với ID hợp lệ")
    public void testDeleteWithValidModel() {
        try {
            khachHangDAO.delete("KH03");
        } catch (Exception e) {
            Assert.fail("Xóa với ID hợp lệ không nên ném ra ngoại lệ");
        }
    }

    @Test(description = "Kiểm thử lấy tất cả")
    public void testSelectAll() {
        List<KhachHang> khachHangs = khachHangDAO.selectALl();
        Assert.assertNotNull(khachHangs);
    }

    @Test(description = "Kiểm thử lấy theo ID")
    public void testSelectById() {
        KhachHang khachHang = khachHangDAO.selectById("KH00001");
        Assert.assertNotNull(khachHang);
    }

    @Test(description = "Kiểm thử tìm kiếm theo Mã KH hoặc Tên KH")
    public void testTimKiemByMaKHOrHoTen() {
        List<KhachHang> khachHangs = khachHangDAO.timKiem("KH01", true);
        Assert.assertNotNull(khachHangs);
    }

    @Test(description = "Kiểm thử lấy theo SDT")
    public void testSelectBySDT() {
        KhachHang khachHang = khachHangDAO.selectBySDT("0987654321");
        Assert.assertNotNull(khachHang);
    }

    @Test(description = "Kiểm thử lấy theo Email")
    public void testSelectByEmail() {
        KhachHang khachHang = khachHangDAO.selectByEmail("nguyenthif@gmail.com");
        Assert.assertNotNull(khachHang);
    }
}
