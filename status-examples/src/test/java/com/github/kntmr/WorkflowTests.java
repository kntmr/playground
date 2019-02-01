package com.github.kntmr;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class WorkflowTests {

    enum UserRole {
        依頼者,
        管理者,
        作業者,
        システム
    }

    enum State {
        ドラフト("00", "ドラフト", "001", "ドラフト"),
        受付前("10", "受付前", "001", "受付前"),
        作業中_未着手("20", "作業中", "001", "未着手"),
        作業中_作業中("20", "作業中", "002", "作業中"),
        作業中_作業完了("20", "作業中", "003", "作業完了"),
        完了("30", "完了", "001", "完了"),
        対応不可("90", "対応不可", "001", "対応不可");
        final String acceptCode;
        final String acceptName;
        final String workCode;
        final String workName;
        private State(String acceptCode, String acceptName, String workCode, String workName) {
            this.acceptCode = acceptCode;
            this.acceptName = acceptName;
            this.workCode = workCode;
            this.workName = workName;
        }
    }

    boolean canNext(UserRole userRole, State current, State next) {
        if (userRole == UserRole.依頼者) {
            return current == State.ドラフト && next == State.ドラフト;
        }
        if (userRole == UserRole.作業者) {
            return current.acceptCode.equals("20") && next.acceptCode.equals("20");
        }
        if (userRole == UserRole.管理者 || userRole == UserRole.システム) {
            return true;
        }
        throw new AssertionError("Not yet implemented");
    }

    @Test
    public void test01_依頼者() {
        // ドラフトは維持できる
        // ドラフト以外には変更できない
        UserRole target = UserRole.依頼者;
        assertThat(canNext(target, State.ドラフト, State.ドラフト), is(true));
        assertThat(canNext(target, State.ドラフト, State.受付前), is(false));
        assertThat(canNext(target, State.ドラフト, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.ドラフト, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.ドラフト, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.ドラフト, State.完了), is(false));
        assertThat(canNext(target, State.ドラフト, State.対応不可), is(false));

        assertThat(canNext(target, State.受付前, State.ドラフト), is(false));
        assertThat(canNext(target, State.受付前, State.受付前), is(false));
        assertThat(canNext(target, State.受付前, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.受付前, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.受付前, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.受付前, State.完了), is(false));
        assertThat(canNext(target, State.受付前, State.対応不可), is(false));

        assertThat(canNext(target, State.作業中_未着手, State.ドラフト), is(false));
        assertThat(canNext(target, State.作業中_未着手, State.受付前), is(false));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.作業中_未着手, State.完了), is(false));
        assertThat(canNext(target, State.作業中_未着手, State.対応不可), is(false));

        assertThat(canNext(target, State.作業中_作業中, State.ドラフト), is(false));
        assertThat(canNext(target, State.作業中_作業中, State.受付前), is(false));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.作業中_作業中, State.完了), is(false));
        assertThat(canNext(target, State.作業中_作業中, State.対応不可), is(false));

        assertThat(canNext(target, State.作業中_作業完了, State.ドラフト), is(false));
        assertThat(canNext(target, State.作業中_作業完了, State.受付前), is(false));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.作業中_作業完了, State.完了), is(false));
        assertThat(canNext(target, State.作業中_作業完了, State.対応不可), is(false));

        assertThat(canNext(target, State.完了, State.ドラフト), is(false));
        assertThat(canNext(target, State.完了, State.受付前), is(false));
        assertThat(canNext(target, State.完了, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.完了, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.完了, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.完了, State.完了), is(false));
        assertThat(canNext(target, State.完了, State.対応不可), is(false));

        assertThat(canNext(target, State.対応不可, State.ドラフト), is(false));
        assertThat(canNext(target, State.対応不可, State.受付前), is(false));
        assertThat(canNext(target, State.対応不可, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.対応不可, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.対応不可, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.対応不可, State.完了), is(false));
        assertThat(canNext(target, State.対応不可, State.対応不可), is(false));
    }

    @Test
    public void test01_作業者() {
        // 作業中は維持/変更できる
        // 作業中以外には変更できない
        UserRole target = UserRole.作業者;
        assertThat(canNext(target, State.ドラフト, State.ドラフト), is(false));
        assertThat(canNext(target, State.ドラフト, State.受付前), is(false));
        assertThat(canNext(target, State.ドラフト, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.ドラフト, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.ドラフト, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.ドラフト, State.完了), is(false));
        assertThat(canNext(target, State.ドラフト, State.対応不可), is(false));

        assertThat(canNext(target, State.受付前, State.ドラフト), is(false));
        assertThat(canNext(target, State.受付前, State.受付前), is(false));
        assertThat(canNext(target, State.受付前, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.受付前, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.受付前, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.受付前, State.完了), is(false));
        assertThat(canNext(target, State.受付前, State.対応不可), is(false));

        assertThat(canNext(target, State.作業中_未着手, State.ドラフト), is(false));
        assertThat(canNext(target, State.作業中_未着手, State.受付前), is(false));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.完了), is(false));
        assertThat(canNext(target, State.作業中_未着手, State.対応不可), is(false));

        assertThat(canNext(target, State.作業中_作業中, State.ドラフト), is(false));
        assertThat(canNext(target, State.作業中_作業中, State.受付前), is(false));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.完了), is(false));
        assertThat(canNext(target, State.作業中_作業中, State.対応不可), is(false));

        assertThat(canNext(target, State.作業中_作業完了, State.ドラフト), is(false));
        assertThat(canNext(target, State.作業中_作業完了, State.受付前), is(false));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.完了), is(false));
        assertThat(canNext(target, State.作業中_作業完了, State.対応不可), is(false));

        assertThat(canNext(target, State.完了, State.ドラフト), is(false));
        assertThat(canNext(target, State.完了, State.受付前), is(false));
        assertThat(canNext(target, State.完了, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.完了, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.完了, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.完了, State.完了), is(false));
        assertThat(canNext(target, State.完了, State.対応不可), is(false));

        assertThat(canNext(target, State.対応不可, State.ドラフト), is(false));
        assertThat(canNext(target, State.対応不可, State.受付前), is(false));
        assertThat(canNext(target, State.対応不可, State.作業中_未着手), is(false));
        assertThat(canNext(target, State.対応不可, State.作業中_作業中), is(false));
        assertThat(canNext(target, State.対応不可, State.作業中_作業完了), is(false));
        assertThat(canNext(target, State.対応不可, State.完了), is(false));
        assertThat(canNext(target, State.対応不可, State.対応不可), is(false));
    }

    @Test
    public void test01_管理者() {
        // いずれのステータスを変更できる
        UserRole target = UserRole.管理者;
        assertThat(canNext(target, State.ドラフト, State.ドラフト), is(true));
        assertThat(canNext(target, State.ドラフト, State.受付前), is(true));
        assertThat(canNext(target, State.ドラフト, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.ドラフト, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.ドラフト, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.ドラフト, State.完了), is(true));
        assertThat(canNext(target, State.ドラフト, State.対応不可), is(true));

        assertThat(canNext(target, State.受付前, State.ドラフト), is(true));
        assertThat(canNext(target, State.受付前, State.受付前), is(true));
        assertThat(canNext(target, State.受付前, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.受付前, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.受付前, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.受付前, State.完了), is(true));
        assertThat(canNext(target, State.受付前, State.対応不可), is(true));

        assertThat(canNext(target, State.作業中_未着手, State.ドラフト), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.受付前), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.完了), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.対応不可), is(true));

        assertThat(canNext(target, State.作業中_作業中, State.ドラフト), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.受付前), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.完了), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.対応不可), is(true));

        assertThat(canNext(target, State.作業中_作業完了, State.ドラフト), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.受付前), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.完了), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.対応不可), is(true));

        assertThat(canNext(target, State.完了, State.ドラフト), is(true));
        assertThat(canNext(target, State.完了, State.受付前), is(true));
        assertThat(canNext(target, State.完了, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.完了, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.完了, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.完了, State.完了), is(true));
        assertThat(canNext(target, State.完了, State.対応不可), is(true));

        assertThat(canNext(target, State.対応不可, State.ドラフト), is(true));
        assertThat(canNext(target, State.対応不可, State.受付前), is(true));
        assertThat(canNext(target, State.対応不可, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.対応不可, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.対応不可, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.対応不可, State.完了), is(true));
        assertThat(canNext(target, State.対応不可, State.対応不可), is(true));
    }

    @Test
    public void test01_システム() {
        // いずれのステータスを変更できる
        UserRole target = UserRole.システム;
        assertThat(canNext(target, State.ドラフト, State.ドラフト), is(true));
        assertThat(canNext(target, State.ドラフト, State.受付前), is(true));
        assertThat(canNext(target, State.ドラフト, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.ドラフト, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.ドラフト, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.ドラフト, State.完了), is(true));
        assertThat(canNext(target, State.ドラフト, State.対応不可), is(true));

        assertThat(canNext(target, State.受付前, State.ドラフト), is(true));
        assertThat(canNext(target, State.受付前, State.受付前), is(true));
        assertThat(canNext(target, State.受付前, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.受付前, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.受付前, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.受付前, State.完了), is(true));
        assertThat(canNext(target, State.受付前, State.対応不可), is(true));

        assertThat(canNext(target, State.作業中_未着手, State.ドラフト), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.受付前), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.完了), is(true));
        assertThat(canNext(target, State.作業中_未着手, State.対応不可), is(true));

        assertThat(canNext(target, State.作業中_作業中, State.ドラフト), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.受付前), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.完了), is(true));
        assertThat(canNext(target, State.作業中_作業中, State.対応不可), is(true));

        assertThat(canNext(target, State.作業中_作業完了, State.ドラフト), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.受付前), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.完了), is(true));
        assertThat(canNext(target, State.作業中_作業完了, State.対応不可), is(true));

        assertThat(canNext(target, State.完了, State.ドラフト), is(true));
        assertThat(canNext(target, State.完了, State.受付前), is(true));
        assertThat(canNext(target, State.完了, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.完了, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.完了, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.完了, State.完了), is(true));
        assertThat(canNext(target, State.完了, State.対応不可), is(true));

        assertThat(canNext(target, State.対応不可, State.ドラフト), is(true));
        assertThat(canNext(target, State.対応不可, State.受付前), is(true));
        assertThat(canNext(target, State.対応不可, State.作業中_未着手), is(true));
        assertThat(canNext(target, State.対応不可, State.作業中_作業中), is(true));
        assertThat(canNext(target, State.対応不可, State.作業中_作業完了), is(true));
        assertThat(canNext(target, State.対応不可, State.完了), is(true));
        assertThat(canNext(target, State.対応不可, State.対応不可), is(true));
    }

    enum Authority {
        READONLY,
        READWRITE,
        NONE;
    }

    Authority getAuthority(UserRole userRole, State current) {
        if (userRole == UserRole.依頼者) {
            return current == State.ドラフト ? Authority.READWRITE : Authority.NONE;
        }
        if (userRole == UserRole.作業者) {
            return current.acceptCode.equals("20") ? Authority.READWRITE : Authority.NONE;
        }
        if (userRole == UserRole.管理者 || userRole == UserRole.システム) {
            return Authority.READWRITE;
        }
        throw new AssertionError("Not yet implemented");
    }

    @Test
    public void test02_依頼者() {
        // ドラフトのみ閲覧/編集可
        // ドラフト以外は閲覧不可
        UserRole target = UserRole.依頼者;
        assertThat(getAuthority(target, State.ドラフト), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.受付前), is(Authority.NONE));
        assertThat(getAuthority(target, State.作業中_未着手), is(Authority.NONE));
        assertThat(getAuthority(target, State.作業中_作業中), is(Authority.NONE));
        assertThat(getAuthority(target, State.作業中_作業完了), is(Authority.NONE));
        assertThat(getAuthority(target, State.完了), is(Authority.NONE));
        assertThat(getAuthority(target, State.対応不可), is(Authority.NONE));
    }

    @Test
    public void test02_作業者() {
        // 作業中は閲覧/編集可
        // 作業中以外は閲覧不可
        UserRole target = UserRole.作業者;
        assertThat(getAuthority(target, State.ドラフト), is(Authority.NONE));
        assertThat(getAuthority(target, State.受付前), is(Authority.NONE));
        assertThat(getAuthority(target, State.作業中_未着手), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.作業中_作業中), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.作業中_作業完了), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.完了), is(Authority.NONE));
        assertThat(getAuthority(target, State.対応不可), is(Authority.NONE));
    }

    @Test
    public void test02_管理者() {
        // いずれのステータスを閲覧/編集可
        UserRole target = UserRole.管理者;
        assertThat(getAuthority(target, State.ドラフト), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.受付前), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.作業中_未着手), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.作業中_作業中), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.作業中_作業完了), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.完了), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.対応不可), is(Authority.READWRITE));
    }

    @Test
    public void test02_システム() {
        // いずれのステータスを閲覧/編集可
        UserRole target = UserRole.システム;
        assertThat(getAuthority(target, State.ドラフト), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.受付前), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.作業中_未着手), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.作業中_作業中), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.作業中_作業完了), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.完了), is(Authority.READWRITE));
        assertThat(getAuthority(target, State.対応不可), is(Authority.READWRITE));
    }

}
