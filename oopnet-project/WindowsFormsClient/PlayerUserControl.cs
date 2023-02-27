using DataLayer;
using DataLayer.Models;
using System;
using System.Drawing;
using System.IO;
using System.Reflection;
using System.Resources;
using System.Windows.Forms;
using static DataLayer.Models.Enums;

namespace WindowsFormsClient
{
    public partial class PlayerUserControl : UserControl
    {
        private readonly string PATH = Path.GetDirectoryName(Assembly.GetExecutingAssembly().Location);
        private static int count = 0;
        private Gender gender;

        public MatchPlayer Player { get; private set; }

        public PlayerUserControl() { }

        public PlayerUserControl(Gender gender, MatchPlayer player, int favCount)
        {
            InitializeComponent();
            Player = player;
            this.gender = gender;
            FillData(player);
            count = favCount;
        }

        public void ResetCounter()
        {
            count = 0;
        }

        private void FillData(MatchPlayer player)
        {
            Bitmap playerImageHolder, playerImage;

            playerImageHolder = gender == Gender.Men ? Resources.malePlaceHolder : Resources.femalePlaceholder;
            playerImage = (Bitmap)Utils.LoadImageFromFile(PATH, Player.Name.ToLower().Trim().Replace(" ", ""));
            lblName.Text = player.Name;
            lblPosition.Text = Enum.GetName(typeof(Position), player.Position);
            lblShirtNumber.Text = player.ShirtNumber.ToString();

            if (playerImage != null)
            {
                pbPlayer.Image = playerImage;
            }
            else
            {
                pbPlayer.Image = playerImageHolder;
            }

            if (player.Captain)
            {
                cbCaptain.Checked = true;
            }
            if (player.Favourite)
            {
                cbFavouritePlayer.Checked = true;
            }
        }

        private void panel1_Click(object sender, EventArgs e)
        {
            PlayerUserControl puc = (sender as Panel).Parent as PlayerUserControl;

            if (puc.Parent.Name == "flpPlayers")
            {
                if (puc.BorderStyle == BorderStyle.None)
                {
                    if (count < 3)
                    {
                        puc.BorderStyle = BorderStyle.Fixed3D;
                        puc.Player.Favourite = true;
                        ++count;
                    }
                }
                else
                {
                    puc.BorderStyle = BorderStyle.None;
                    puc.Player.Favourite = false;
                    --count;
                }
            }
            else
            {
                if (puc.BorderStyle == BorderStyle.None)
                {
                    puc.BorderStyle = BorderStyle.Fixed3D;
                    puc.Player.Favourite = false;
                }
                else
                {
                    puc.BorderStyle = BorderStyle.None;
                    puc.Player.Favourite = true;
                }
            }

            if (puc.Parent.Name == "flpPlayers")
            {
                ContextMenuStrip = cmsPlayerAddFavourites;
            }
            else
            {
                ContextMenuStrip = cmsPlayerRemoveFavourites;
            }
        }

        private void addToFavouritesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            (ParentForm as MainForm).AddToFavourites();
        }

        private void removeFromFavouritesToolStripMenuItem_Click(object sender, EventArgs e)
        {
            (ParentForm as MainForm).RemoveFromFavourites();
        }

        private void panel1_MouseDown(object sender, MouseEventArgs e)
        {
            if (ModifierKeys == Keys.Control)
            {
                ((sender as Panel).Parent as PlayerUserControl).DoDragDrop(((sender as Panel).Parent as PlayerUserControl), DragDropEffects.Move);
            }
        }

        private void pbPlayer_MouseEnter(object sender, EventArgs e)
        {
            (sender as PictureBox).BorderStyle = BorderStyle.Fixed3D;
        }

        private void pbPlayer_MouseLeave(object sender, EventArgs e)
        {
            (sender as PictureBox).BorderStyle = BorderStyle.FixedSingle;
        }

        private void pbPlayer_Click(object sender, EventArgs e)
        {
            using (OpenFileDialog dialog = new OpenFileDialog())
            {
                dialog.Filter = "Pictures|*.gif;*.jpg;*.jpeg;*.png;|All files|*.*";
                dialog.InitialDirectory = Application.StartupPath;
                if (dialog.ShowDialog() == DialogResult.OK)
                {
                    try
                    {
                        Bitmap bmp = new Bitmap(dialog.FileName);
                        Bitmap smallBmp = new Bitmap(bmp, new Size(300, 300));
                        (sender as PictureBox).Image = smallBmp;
                        Player.Picture = smallBmp;
                        Utils.SaveImageToFile((Image)smallBmp, PATH, Player.Name.ToLower().Trim().Replace(" ", ""));
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show($"Cannot open picture\n\n{ex.Message}");
                    }
                }
            }
        }
    }
}
