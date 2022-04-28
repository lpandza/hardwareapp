package hr.tvz.pandza.hardwareapp.repository.impl;

import hr.tvz.pandza.hardwareapp.enums.Type;
import hr.tvz.pandza.hardwareapp.model.Hardware;
import hr.tvz.pandza.hardwareapp.repository.HardwareRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class HardwareRepositoryJdbc implements HardwareRepository {

    private static final String SELECT_ALL = "SELECT * FROM HARDWARE";

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert inserter;

    public HardwareRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.inserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public List<Hardware> findAll() {
        return jdbcTemplate.query(SELECT_ALL, this::mapRowToHardware);
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional
                    .of(jdbcTemplate.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code));
        }
        catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        addHardware(hardware);
        return Optional.of(hardware);
    }

    @Override
    public boolean deleteByCode(String code) {
        int rows = jdbcTemplate.update("DELETE FROM hardware WHERE CODE = ?", code);

        return rows > 0;
    }

    @Override
    public Optional<Hardware> update(String code, Hardware hardware) {
        int rows = updateHardware(code, hardware);

        if (rows > 0) return Optional.of(hardware);
        return Optional.empty();
    }

    private long addHardware(Hardware hardware){
        Map<String, Object> params = new HashMap<>();

        params.put("code", hardware.getCode());
        params.put("name", hardware.getName());
        params.put("price", hardware.getPrice());
        params.put("type", hardware.getType());
        params.put("quantity", hardware.getQuantity());

        return inserter.executeAndReturnKey(params).longValue();
    }

    private int updateHardware(String code, Hardware hardware){
        System.out.println(hardware);
         return jdbcTemplate.update("UPDATE hardware SET " +
                "code = ?, " +
                "name = ?, " +
                "price = ?, " +
                "type = ?, " +
                "quantity = ? " +
                "WHERE code = ?",
                hardware.getCode(),
                hardware.getName(),
                hardware.getPrice(),
                hardware.getType().toString(),
                hardware.getQuantity(),
                 hardware.getCode()
                );
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getLong("id"),
                rs.getString("code"),
                rs.getString("name"),
                rs.getDouble("price"),
                Type.valueOf(rs.getString("type")),
                rs.getInt("quantity")
        );
    }

}
