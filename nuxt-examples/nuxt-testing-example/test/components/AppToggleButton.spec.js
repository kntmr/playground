import AppToggleButton from '~/components/AppToggleButton.vue'
import { mount } from '@vue/test-utils'

describe('AppToggleButton.vue', () => {
  let wrapper
  beforeEach(() => {
    wrapper = null
    wrapper = mount(AppToggleButton)
  })
  test('default', () => {
    expect(wrapper.find('p').text()).toBe('off')
  })
  test('on', async () => {
    wrapper.find('button').trigger('click')
    await wrapper.vm.$nextTick()
    expect(wrapper.find('p').text()).toBe('on')
  })
})