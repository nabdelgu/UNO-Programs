# == Schema Information
#
# Table name: products
#
#  id         :integer          not null, primary key
#  name       :string
#  price      :string
#  created_at :datetime         not null
#  updated_at :datetime         not null
#

class Product < ActiveRecord::Base

  has_attached_file :image, :styles => {large: "600x600", medium: "300x300", thumb: "150x150#"}
  validates_attachment_content_type :image, content_type: /\Aimage\/.*\Z/

  belongs_to :user
  belongs_to :cart

  validates :name, presence: true, length: { maximum: 25 }
  validates :price, presence: true, length: { maximum: 25 }
end
