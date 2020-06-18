<template>
  <section class="container posts-page">
    <el-card>
      <div slot="header" class="clearfix">
        <span>新着投稿</span>
      </div>
      <el-table :data="showPosts" class="table" style="width:100%">
        <el-table-column prop="title" label="タイトル"></el-table-column>
        <el-table-column prop="user.id" label="投稿者" width="180"></el-table-column>
        <el-table-column prop="created_at" label="投稿日時" width="240"></el-table-column>
      </el-table>
    </el-card>
  </section>
</template>

<script>
export default {
  computed: {
    showPosts () {
      return [
        {
          id: '001',
          title: 'Title 1',
          body: 'Content 1 ...',
          created_at: '2020/06/18 12:30:00',
          user: {
            id: 'kntmr'
          }
        },
        {
          id: '002',
          title: 'Title 2',
          body: 'Content 2 ...',
          created_at: '2020/06/18 18:45:00',
          user: {
            id: 'kntmr'
          }
        }
      ]
    }
  }
}
</script>

<style>
.posts-page .el-table__row {
  cursor: pointer;
}
</style>