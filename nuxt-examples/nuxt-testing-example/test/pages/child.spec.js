import ChildPage from '~/pages/child'
import { mount, createLocalVue, RouterLinkStub } from '@vue/test-utils'

const localVue = createLocalVue()

describe('pages/child.vue', () => {
  test('TopPage', () => {
    const wrapper = mount(ChildPage, {
      localVue,
      stubs: {
        NuxtLink: RouterLinkStub
      }
    })
    expect(wrapper.findComponent(RouterLinkStub).props().to).toBe('/')
  })
})